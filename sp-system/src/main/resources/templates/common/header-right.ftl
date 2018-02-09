<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="header-right">
	<ul class="headermenu">
		<li>
			<div class="btn-group">
				<button type="button" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown">
					${sessionScope.user.nickName } <span class="caret"></span>
				</button>
				<ul class="dropdown-menu dropdown-menu-usermenu pull-right">
					<li><a data-toggle="modal" data-target="#userModal"
						href="javascript:void(0);" onclick="findUserInfo();"><i
							class="glyphicon glyphicon-user"></i> 个人信息</a></li>
					<li><a data-toggle="modal" data-target="#updateUserPwdModal"
						href="javascript:void(0);" onclick="updateUserPWD();"><i
							class="glyphicon glyphicon-lock"></i> <span>修改密码</span></a></li>
					<li><a
						href="<%=request.getContextPath()%>/j_spring_security_logout"><i
							class="glyphicon glyphicon-log-out"></i> 退出登录</a></li>
				</ul>
			</div>
		</li>
	</ul>
</div>

<div class="modal fade bs-example-modal-lg" id="updateUserPwdModal"
			tabindex="-1" role="dialog"
			aria-labelledby="updateUserPwdModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true" id="closeUpdateUserPwdBody">&times;</button>
						<h4 class="modal-title" id="titleModal">修改密码</h4>
					</div>
					<div class="modal-body" id="updateUserPwdModalBody">
						<div class="tab-content">
							<form id="updateUserPwdForm" name="submitForm"
								data-option="edit" action="" class="form-horizontal"
								method="post" accept-charset="UTF-8"
								enctype="application/x-www-form-urlencoded">
								<input type="hidden" name="id" id="updatePwdUserId" value="${sessionScope.user.id }">
								<div class="panel panel-default">
									<div class="panel-body">
										<div class="form-group">
											<div class="form-group">
												<label class="col-sm-3 control-label">旧密码：</label>
												<div class="col-sm-4">
													<input type="password" name="oldPassword"
														class="form-control" placeholder="请输入旧密码" /> <label
														class="error" for="oldPassword"></label>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">新密码：</label>
												<div class="col-sm-4">
													<input type="password" name="password" id="userPassword"
														class="form-control" placeholder="请输入新密码" /> <label
														class="error" for="password"></label>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">确认新密码：</label>
												<div class="col-sm-4">
													<input type="password" 
														name="confirmPassword" class="form-control"
														placeholder="请再次输入新密码" /> <label class="error"
														for="confirmPassword"></label>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-9 col-sm-offset-3">
												<button id="submitUpdateUserPwdBtn" class="btn btn-primary"
													name="submitBtn" data-option="edit" type="submit">提交</button>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="modal fade bs-example-modal-lg" id="userModal"
			tabindex="-1" role="dialog"
			aria-labelledby="userModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true" id="closeUserBody">&times;</button>
						<h4 class="modal-title" id="titleModal">用户信息</h4>
					</div>
					<div class="modal-body" id="UserModalBody">
						<div class="tab-content">
							<form id="UserForm"action="" class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-2 control-label">用户名：</label>
									<div class="col-sm-4">
										<input type="text" name="nickName"
											class="form-control" value="${sessionScope.user.nickName }"  disabled="disabled"  />
									</div>
									<label class="col-sm-2 control-label">姓名：</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" 
										value="${sessionScope.user.name }"  disabled="disabled"  />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">手机号码：</label>
									<div class="col-sm-4">
										<input type="text" class="form-control"
										value="${sessionScope.user.phone }"  disabled="disabled"  />
									</div>
									<label class="col-sm-2 control-label">邮箱：</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" 
										value="${sessionScope.user.email }"  disabled="disabled"  />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">性别：</label>
									<div class="col-sm-4">
										<select id="sex" name="sex" class="select2"  disabled="disabled" >
											<option value="1" <c:if test="${sessionScope.user.sex == 'male' }"> selected="selected" </c:if>>男</option>
											<option value="2" <c:if test="${sessionScope.user.sex == 'female' }"> selected="selected" </c:if>>女</option>
										</select>
									</div>
									<label class="col-sm-2 control-label">状态：</label>
									<div class="col-sm-4">
										<select id="userStatus" name="userStatus" class="select2"
											disabled="disabled" >
											<option value="1" <c:if test="${sessionScope.user.status == '1' }"> selected="selected" </c:if>>可用</option>
											<option value="2" <c:if test="${sessionScope.user.status == '0' }"> selected="selected" </c:if>>禁用</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">连续登录失败次数：</label>
									<div class="col-sm-4">
										<input type="text" disabled="disabled"
											value="${sessionScope.user.failNum }"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">创建时间：</label>
									<div class="col-sm-4">
										<input type="text" disabled="disabled"
											value='<fmt:formatDate value="${sessionScope.user.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />'
											class="form-control" />
									</div>
									<label class="col-sm-2 control-label">修改时间：</label>
									<div class="col-sm-4">
										<input type="text" disabled="disabled"
											value='<fmt:formatDate value="${sessionScope.user.updateTime }" pattern="yyyy-MM-dd HH:mm:ss" />'
											class="form-control" />
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
