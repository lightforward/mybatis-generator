$(function () {

    // 自定义主题
    // var theme = {
    //     //
    //     color: [
    //         '#c12e34','#e6b600','#0098d9','#2b821d',
    //         '#005eaa','#339ca8','#cda819','#32a487'
    //     ],
    // };

    // 基于准备好的dom，初始化echarts实例
    var barEhart = echarts.init(document.getElementById('bar'), 'westeros');
    // 指定图表的配置项和数据
    var optionBar = {
        title: {
            mark : '辅助线开关',
            markUndo : '删除辅助线',
            markClear : '清空辅助线',
            text: 'ECharts柱状图'
        },
        tooltip: {},
        legend: {
            data: ['销量']
        },
        xAxis: {
            data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };
    // 使用刚指定的配置项和数据显示图表
    barEhart.setOption(optionBar);



    var linieChart = echarts.init(document.getElementById('line'), 'westeros');
    var optionLine = {
        title : {
            text: '某地区蒸发量和降水量',
            subtext: ''
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['蒸发量','降水量']
        },
        toolbox: {
            show : true,
            feature : {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'蒸发量',
                type:'bar',
                data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
                markPoint : {
                    data : [
                        {type : 'max', name: '最大值'},
                        {type : 'min', name: '最小值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
            },
            {
                name:'降水量',
                type:'bar',
                data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
                markPoint : {
                    data : [
                        {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183},
                        {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name : '平均值'}
                    ]
                }
            }
        ]
    };
    linieChart.setOption(optionLine);



    loadMap("china", "china", chinaData);

});

function loadMap(mapCode, mapName, data) {
    var mapChart = echarts.init(document.getElementById('map'), 'westeros');
    $.get('../../echarts/map/' + mapCode + ".json", function (chinaJson) {
        echarts.registerMap(mapCode, chinaJson);
        optionMap = {
            title: {
                text: '销量',
                subtext: '地图demo',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            visualMap: {
                min: 0,
                max: 2500,
                left: 'left',
                top: 'bottom',
                text: ['高','低'],           // 文本，默认为数值文本
                calculable: true
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    dataView: {readOnly: false},
                    restore: {},
                    saveAsImage: {}
                }
            },
            series: [
                {
                    name: '地图demo',
                    type: 'map',
                    mapType: mapCode,
                    zoom: 1,      //当前视角的缩放比例
                    roam: true,   //是否开启平游或缩放
                    scaleLimit: { //滚轮缩放的极限控制
                        min: 0.5,
                        max: 2
                    },
                    label: {
                        normal: {
                            show: true
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data: data
                }
            ]
        };
        mapChart.setOption(optionMap);

        //定义setTimeout执行方法, 单击事件处理代码加timer里，延迟执行单击，如果单击第二下的时候第一下还没有执行就改执行双击代码
        var TimeFn = null;
        //绑定单击事件
        mapChart.on('click', echartsMapClick);
        function echartsMapClick(params) {
            clearTimeout(TimeFn);
            TimeFn = setTimeout(function(){
                mapChart.dispose();
                loadMap("610000", "shanxi", shanxiData);
            },300);
        }

        //绑定双击击事件
        mapChart.on('dblclick', echartsMapDblclick);
        function echartsMapDblclick(params) {
            clearTimeout(TimeFn);
            mapChart.dispose();
            loadMap("china", "china", chinaData);
        }

    })
}

