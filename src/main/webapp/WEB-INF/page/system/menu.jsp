<%@ page language="java" pageEncoding="UTF-8"%>
<div class="admin-sidebar" style="padding-top:51px">
	<ul class="am-list admin-sidebar-list">
		<li><a href="http://demo.mycodes.net/houtai/Amaze/admin-index.html">
				<span class="am-icon-home"></span> 首页
			</a></li>
		<li class="admin-parent"><a class="am-cf am-collapsed" data-am-collapse="{target: &#39;#collapse-nav&#39;}">
				<span class="am-icon-file"></span> 页面模块 <span class="am-icon-angle-right am-fr am-margin-right"></span>
			</a>
			<ul class="am-list am-collapse admin-sidebar-sub" id="collapse-nav">
				<li><a href="http://demo.mycodes.net/houtai/Amaze/admin-user.html" class="am-cf">
						<span class="am-icon-check"></span> 个人资料<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span>
					</a></li>
				<li><a href="http://demo.mycodes.net/houtai/Amaze/admin-help.html">
						<span class="am-icon-puzzle-piece"></span> 帮助页
					</a></li>
				<li><a href="http://demo.mycodes.net/houtai/Amaze/admin-gallery.html">
						<span class="am-icon-th"></span> 相册页面<span class="am-badge am-badge-secondary am-margin-right am-fr">24</span>
					</a></li>
				<li><a href="http://demo.mycodes.net/houtai/Amaze/admin-log.html">
						<span class="am-icon-calendar"></span> 系统日志
					</a></li>
				<li><a href="http://demo.mycodes.net/houtai/Amaze/admin-404.html">
						<span class="am-icon-bug"></span> 404
					</a></li>
			</ul></li>
		<li><a href="http://demo.mycodes.net/houtai/Amaze/admin-table.html">
				<span class="am-icon-table"></span> 表格
			</a></li>
		<li><a href="${project}/article/list.html" target="content">
				<span class="am-icon-pencil-square-o"></span> 文章管理
			</a></li>
		<li><a href="${project}/category/list.html" target="content">
				<span class="am-icon-reorder"></span> 分类管理
			</a></li>
		<li><a href="https://tieba.baidu.com" target="content">
				<span class="am-icon-sign-out"></span> 注销
			</a></li>
	</ul>

	<div class="am-panel am-panel-default admin-sidebar-panel">
		<div class="am-panel-bd">
			<p>
				<span class="am-icon-bookmark"></span> 公告
			</p>
			<p>时光静好，与君语；细水流年，与君同。—— Amaze</p>
		</div>
	</div>

	<div class="am-panel am-panel-default admin-sidebar-panel">
		<div class="am-panel-bd">
			<p>
				<span class="am-icon-tag"></span> wiki
			</p>
			<p>Welcome to the Amaze</p>
		</div>
	</div>
</div>