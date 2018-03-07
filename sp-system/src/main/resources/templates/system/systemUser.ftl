<!DOCTYPE HTML>
<#include "../common/master.ftl"/>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="author" content="chenyingjun">
<title>系统用户管理</title>
<#include "../common/resources.ftl"/>
</head>
<body>
	<!-- Preloader -->
	<div id="preloader">
		<div id="status">
			<i class="fa fa-spinner fa-spin"></i>
		</div>
	</div>
	<section>
		<div class="leftpanel">
		<#include "../common/header-left.ftl"/>
			<!-- logopanel -->

			<div class="leftpanelinner">
			<#include "../common/header-left-sm.ftl"/>
			<#include "../common/menu.ftl"/>
			</div>
			<!-- leftpanelinner -->
		</div>
		<!-- leftpanel -->

		<div class="mainpanel">

			<div class="headerbar">

				<a class="menutoggle"><i class="fa fa-bars"></i></a>
				<#include "../common/header-right.ftl"/>
				<!-- header-right -->

			</div>
			<!-- headerbar -->

			<div class="pageheader">
				<h2>
					<i class="fa fa-user"></i> 系统用户管理
				</h2>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						  <li><a href="${request.contextPath}/main">业务管理系统</a></li>
						<li>系统管理</li>
						<li class="active"><a href="${request.contextPath}/systemuser/">系统用户管理</a></li>
					</ol>
				</div>
			</div>
			<div class="contentpanel">
				<div class="row">
					<!-- panel -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<form id="serachForm" name="serachForm" action="#">
								<input type="hidden" id="pageNum" name="pageNum"
									class="form-control mg-bm10" value="1" />
								<div class="form-group">
									<div class="col-sm-1" style="width: auto;">
										<select id="pageSize" class="select2 mb10" required
											data-placeholder="每页记录数" name="pageSize">
											<option value="15" selected="selected">15</option>
											<option value="30">30</option>
											<option value="50">50</option>
											<option value="100">100</option>
											<option value="200">200</option>
											<option value="500">500</option>
										</select><label class="error" for="pageSize"></label>
									</div>
									<div class="col-sm-1">
										<input type="text" id="roleId" name="roleId"
											class="form-control mg-bm10" maxlength="32"
											placeholder="用户角色" /><label class="error"
											for="roleId"></label>
									</div>
									<div class="col-sm-1">
										<input type="text" id="name" name="name"
											class="form-control mg-bm10" maxlength="32"
											placeholder="用户名" /><label class="error"
											for="name"></label>
									</div>
									<div class="col-sm-1">
										<input type="text" id="nickName" name="nickName"
											class="form-control mg-bm10" maxlength="32"
											placeholder="昵称" /><label class="error"
											for="nickName"></label>
									</div>
									<div class="col-sm-1">
										<input type="text" id="phone" name="phone"
											class="form-control mg-bm10" maxlength="32"
											placeholder="用户手机号" /><label class="error"
											for="phone"></label>
									</div>
									<div class="col-sm-1">
										<select id="sex" class="select2"
											data-placeholder="性别" name="sex">
											<option value="">性别</option>
											<option value="male">男</option>
											<option value="female">女</option>
										</select> <label class="error" for="sex"></label>
									</div>
									<div class="col-sm-1">
										<select id="userStatus" class="select2"
											data-placeholder="状态" name="userStatus">
											<option value="">状态</option>
											<option value="0">禁用</option>
											<option value="1">可用</option>
										</select> <label class="error" for="userStatus"></label>
									</div>
									<div class="col-sm-1">
										<input type="text" id="startTime" name="startTime"
											class="form-control mb10"
											onfocus="WdatePicker({maxDate:'#F{$dp.$D(endTime)}',dateFmt:'yyyyMMdd'})"
											placeholder="开始时间" /><label class="error" for="startTime"></label>
									</div>
									<div class="col-sm-1">
										<input type="text" id="endTime" name="endTime"
											class="form-control mb10"
											onfocus="WdatePicker({minDate:'#F{$dp.$D(startTime)}',dateFmt:'yyyyMMdd'})"
											placeholder="结束时间" /><label class="error" for="endTime"></label>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-2">
										<button class="btn btn-success" type="submit">查询</button>
										<a class="btn btn-primary" href="${request.contextPath}/systemuser/add">新增</a>
									</div>
								</div>
							</form>
						</div>
						<!-- panel-heading -->
						<div class="panel-body">
							<div class="table-responsive"
								style="position: relative; min-height: 100px;overflow: scroll;margin-bottom: 10px;">
								<table class="table table-striped" id="systemuserTable">
									<thead>
										<tr>
											<th>序号</th>
											<th>姓名</th>
											<th>昵称</th>
											<th>角色</th>
											<th>邮箱</th>
											<th>手机号</th>
											<th>性别</th>
											<th>失败登录次数</th>
											<th>状态</th>
											<th>创建时间</th>
											<th>修改时间</th>
											<th>操作</th>
										</tr>
									</thead>
								</table>
							</div>
							<!-- table-responsive -->
						</div>
						<!-- panel-body -->
					</div>
					<!-- panel -->
				</div>
			</div>
		</div>
		<!-- contentpanel -->
	</section>
	<script type="text/javascript">
		/*
		 * 加载列表函数
		 * @param data 分页对象包含的信息列表
		 */
		var successFun = function(data) {

			$("#systemuserTable").find("tbody").remove();

			var tbody = '<tbody>';

			for (i in data) {

				tbody = tbody + '<tr>';

				tbody = tbody + '<td>' + data[i].id + '</td>';
				
				var name = data[i].name == undefined ? ""
						: data[i].name;
				
				tbody = tbody + '<td>' + name + '</td>';

				var nickName = data[i].nickName == undefined ? ""
						: data[i].nickName;
				
				tbody = tbody + '<td>' + nickName + '</td>';
				
				
				var roleName = data[i].roleName == undefined ? ""
						: data[i].roleName;
				
				tbody = tbody + '<td>' + roleName + '</td>';

				var email = data[i].email == undefined ? ""
						: data[i].email;
				
				tbody = tbody + '<td>' + email + '</td>';

				var phone = data[i].phone == undefined ? ""
						: data[i].phone;
				
				tbody = tbody + '<td>' + phone + '</td>';

				var sexStr = data[i].sex == undefined ? ""
						: data[i].sex;
				
				if (sexStr == '1') {
					sexStr = "男";
				} else if (sexStr == '2') {
					sexStr = "女";
				}
				
				tbody = tbody + '<td>' + sexStr + '</td>';
				
				var failNum = data[i].failNum == undefined ? ""
						: data[i].failNum;
				
				tbody = tbody + '<td>' + failNum + '</td>';
				
				var status = data[i].status == undefined ? ""
						: data[i].status;
				
				if (status == '0') {
					status = "禁用";
				} else if (status == '1') {
					status = "可用";
				}
				
				tbody = tbody + '<td>' + status + '</td>';
				
				var createTime = data[i].createTime == undefined ? ""
						: new Date(data[i].createTime).format("yyyy-MM-dd");
				
				tbody = tbody + '<td>' + createTime + '</td>';
				
				var updateTime = data[i].updateTime == undefined ? ""
						: new Date(data[i].updateTime).format("yyyy-MM-dd HH:mm:ss");
				
				tbody = tbody + '<td>' + updateTime + '</td>';
				
				tbody = tbody
						+ '<td><a title="编辑" href='
						+ path
						+ '/systemuser/id?id='
						+ data[i].id
						+ '> <i class="fa fa-pencil"></i></a></td>';

				tbody = tbody + '</tr>';
			}

			tbody = tbody + '</tbody>';

			$("#systemuserTable").append(tbody);
			
		};

		/*
		 * 加载数据函数
		 */
		$(function($) {

			table(path + "/systemuser/page", "serachForm",
					"systemuserTable", successFun, true);

			jQuery("#serachForm").validate(
					{

						rules : {
						},
						submitHandler : function() {
							table(path + "/systemuser/page", "serachForm",
									"systemuserTable", successFun, false);
						},
						unhighlight : function(element) {
							jQuery(element).parent().removeClass('has-error');
						},
						highlight : function(element) {
							jQuery(element).parent().removeClass('has-success')
									.addClass('has-error');
						},
						success : function(element) {
							jQuery(element).parent().removeClass('has-error')
									.addClass('has-success');
						}
					});

			// Select2
			jQuery(".select2").select2({
				width : '100%',
				minimumResultsForSearch : -1
			});

		});
	</script>

</body>
</html>
