<%@ page language="java" pageEncoding="UTF-8"%>
<script id="pagination" type="text/template">
<li>共 %{totalSize} 条记录</li>
<li><input class="am-form-field" name="pageNumber" type="number" style="width:50px;height:37px;" minl="1" maxl="%{totalPageNumber}" dt="int" fd="pagination" tip="页码"/></li>
<li><a href="javascript:;" onclick="jumpPage('%{dataUrl}',%{pageSize})">跳页</a></li>
<li><a href="%{dataUrl}?pn=1&ps=%{pageSize}">首页</a></li>
{@if pageNumber > 1}
	<li><a href="%{dataUrl}?pn=%{pageNumber-1}&ps=%{pageSize}">上一页</a></li>
{@else}
	<li class="am-disabled"><a href="javascript:;">上一页</a></li>
{@/if}

{@each i in range(minVisiblePageNumber, maxVisiblePageNumber+1)}
	{@if i == pageNumber}
		<li class="am-active"><a href="%{dataUrl}?pn=%{i}&ps=%{pageSize}">%{i}</a></li>
	{@else}
		<li><a href="%{dataUrl}?pn=%{i}&ps=%{pageSize}">%{i}</a></li>
	{@/if}
{@/each}

{@if pageNumber < totalPageNumber}
	<li><a href="%{dataUrl}?pn=%{pageNumber+1}&ps=%{pageSize}">下一页</a></li>
{@else}
	<li class="am-disabled"><a href="javascript:;">下一页</a></li>
{@/if}
</script>

