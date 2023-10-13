<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Company</title>
</head>
<script type="text/javascript" src="js/require.js"></script>
<body>

<div style="width:100%">
		<div style="margin:20px">
			<input type="button" value="${buttonVal}" id="switchBut"/>
		</div>
	<#if info=="query">
		<div style="margin:20px">
			总计数量:${count}
			最大页数:${maxpagesize}
			当前页:${sizes}
			<br/>
			<br/>
			公司名称:<input type="text" id="name"/>
			页数:<input type="text" id="size"/>
			<input type="button" value="查找" id="but"/>
		</div>
		<table border="1" style="width:100%" cellpadding="0" cellspacing="0">
			<tr style="width:100%">
				<th style="width:30%">公司名称</th>
				<th style="width:10%">时间</th>
				<th style="width:10%">状态</th>
				<th style="width:40%">操作</th>
			</tr>
			<#list companydatas as datas>
				<tr>
					<td>${datas.name}</td>
					<td>${datas.dates}</td>
					<td>${datas.stateInfo}</td>
					<td>
						<a href="/delcompanydata?name=${datas.name}">删除</a>
							<#if datas.stateInfo == "未查阅">
								<a href="/updatecompanydatanotnotice?name=${datas.name}">更新未反馈</a>
								<a href="/updatecompanydataunpassed?name=${datas.name}">更新未通过</a>
							<#elseif datas.stateInfo == "查阅_未反馈">
								<a href="/updatecompanydataunpassed?name=${datas.name}">更新未通过</a>
							<#else>
							</#if>
						
					</td>
				</tr>
			</#list>
		</table>
		<div style="margin:20px;text-align:center">
			<br/>
			<#if sizes == "0">
				<a href="${info}?size=${nextsizes}">下一页</a>
			<#elseif endsizes == "true">
				<a href="${info}?size=${lastsizes}">上一页</a>
			<#else>
				<a href="${info}?size=${lastsizes}">上一页</a>
				<a href="${info}?size=${nextsizes}">下一页</a>
			</#if>
		</div>
	<#else>
		<div style="margin:20px;text-align:center">
			公司名称:<input type="text" id="name"/>
			时间:<input type="text" id="dates"/>
			状态:
			<select  id="state" style="width:170.5px;height:21.5px">
			 <option value="11">未查阅</option>
			 <option value="12">未查阅_不通过</option>
			 <option value="13">未查阅_已过期</option>
			 <option value="21">查阅_已过期</option>
			 <option value="22">查阅_未反馈</option>
			 <option value="23">查阅_通过</option>
			 <option value="24">查阅_不通过</option>
			 <option value="25">工作过</option>
			</select>
			<input type="button" value="提交" id="but"/>
		</div>
	</#if>
	
</div>

</body>
<script type="text/javascript">
  require(["js/company"],function(company){
    var companyObj = new company.company({
      href:"${buttonVal}",
      info:"${info}"
    });
    companyObj.dispose();
  })
</script>

</html>