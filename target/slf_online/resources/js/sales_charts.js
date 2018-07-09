$(function () {
    $('#sales_time').highcharts({
        chart: {
            type: 'line'
        },
        title: {
            text: '2015全年销售业绩走势'
        },
        subtitle: {
            text: '按月统计'
        },
        credits:{
            enabled:false
        },
        xAxis: {
            categories: ['1月份', '2月份', '3月份', '4月份', '5月份', '6月份', '7月份', '8月份', 
            '9月份', '10月份', '11月份', '12月份']
        },
        yAxis: {
            title: {
                text: '销售额/万'
            }
        },
        tooltip: {
            enabled: false,
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'元';
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false
            }
        },
        series: [{
            name: '线上',
            data: [2.5, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
        }, {
            name: '线下',
            data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
        }]
    });

    $('#sales_percent').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '总销售额构成图'
        },
        credits:{
            enabled:false
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b><b>{point.y}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: '各月订单比例',
            data: [
                ['1月份',   25000],
                ['2月份',   18000],
                {
                    name: '三月份',
                    y: 30000,
                    sliced: true,
                    selected: true
                },
                ['4月份',   36000],
                ['5月份',   48000],
                ['6月份',   60000],
                ['7月份',   72000],
                ['8月份',   80000],
                ['9月份',   76000],
                ['10月份',  82000],
                ['11月份',  72000],
                ['12月份',  68000]
            ]
        }]
    });
});