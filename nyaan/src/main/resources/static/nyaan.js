// 直線
Chart.defaults.global.elements.line.tension = 0;
var ctx = document.getElementById("myChart").getContext('2d');
var labelArray = [];
var dataArray = [];
for (var i = 0; i < 60; i++) {
	if (i < 59) {
		labelArray.push(59 - i + '分前');
	} else {
		labelArray.push('今');
	}
	dataArray.push(0);
}
var chart = new Chart(ctx, {
	type: 'line',

	data: {
		labels: labelArray,
		datasets: [{
		label: "にゃーん",
			backgroundColor: 'rgb(255, 99, 132)',
			borderColor: 'rgb(255, 99, 132)',
			data: dataArray,
		}]
	},
	options: {
		animation: false
	}
});
var chartData = chart.data.datasets[0];
var chartDataNum = chartData.data.length - 1;
$(function(){
	// 初期処理
	$.ajax('json',
		{
			type: 'get',
			dataType: 'json'
		}
	)
	// 初期処理完了
	.done(function(data) {
		$('#count').text(data.count);
		chartData.data[chartDataNum] = data.count;
		graph();
	})
	// 更新確認
	setInterval(function() {
		$.ajax('json',
			{
				type: 'get',
				dataType: 'json'
			}
		)
		// 更新確認完了
		.done(function(data) {
			if (data != null && typeof data.count !== undefined) {
				$('#count').text(data.count);
				chartData.data[chartDataNum] = data.count;
				chart.update();	
			}
		})
	}, 2000);
	// グラフ更新(1min.)
	setInterval(function() {
		graph();
	}, 60000);
	// ボタン押下
	$('#button').click(function() {
		$.ajax('json',
			{
				type: 'post',
				dataType: 'json'
			}
		)
		// ボタン押下通信完了
		.done(function(data) {
			if (data != null && typeof data.count !== undefined) {
				$('#count').text(data.count);
				chartData.data[chartDataNum] = data.count;
				chart.update();	
			}
		})
		// 失敗
		.fail(function() {
			alert('にゃーん(通信に失敗しました)');
		});
	});
});
function graph() {
	$.ajax('graph',
		{
			type: 'get',
			dataType: 'json'
		}
	)
	// 更新確認完了
	.done(function(data) {
		for (var i = 0; i < chartDataNum; i++) {
			chartData.data[i] = data.countArray[i];
		}
		chart.update();
	});
}