$(function () {
    $('#order_list').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '2015 各月订单统计'
        },
        subtitle: {
            text: '按月为列'
        },
        credits:{
            enabled:false
        },
        xAxis: {
            categories: [
                '1月份',
                '2月份',
                '3月份',
                '4月份',
                '5月份',
                '6月份',
                '7月份',
                '8月份',
                '9月份',
                '10份',
                '11月份',
                '12月份'
            ]
        },
        yAxis: {
            min: 0,
            title: {
                text: '订单数量（单）'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} 单</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: '线上',
            data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]

        }, {
            name: '线下',
            data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3]

        }]
    });


     $('#order_percent').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '订单比例'
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
                ['1月份',   240],
                ['2月份',   180],
                {
                    name: '三月份',
                    y: 300,
                    sliced: true,
                    selected: true
                },
                ['4月份',   360],
                ['5月份',   480],
                ['6月份',   600],
                ['7月份',   720],
                ['8月份',   800],
                ['9月份',   760],
                ['10月份',  820],
                ['11月份',  720],
                ['12月份',  680]
            ]
        }]
    });
});