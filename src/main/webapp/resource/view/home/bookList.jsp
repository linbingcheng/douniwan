<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style type="text/css">
.booktable {
	height: 100%;
	width: 100%;
	background-color: #E0ECFF;
}
</style>
<div class="booktable">
	<script type="text/javascript">
		function qq(value, name) {
			alert(value + ":" + name)
		}

		$(".add_book_btn").on("click",function(){
			$(".add_book_win").window("open");  // open a window
		});
	</script>

	<div style="background: #efefef; border: 1px solid #ccc;">
	<input id="ss" class="easyui-searchbox" style="width: 300px"
		data-options="searcher:qq,prompt:'请输入图书名或作者名',menu:'#mm'"></input>

	<div id="mm" style="width: 60px">
		<div data-options="name:'all'">全部书</div>
		<div data-options="name:'myAdd'">我发布</div>
		<div data-options="name:'no_accept'">未接收</div>
	</div>
	<a href="#" class="easyui-linkbutton add_book_btn" data-options="iconCls:'icon-add'">发布漂流图书信息</a>

	</div>
<table class="easyui-datagrid"  data-options="pagination:true,pageNumber:1,pageSize:20,striped:true,singleSelect:true">
		<thead>
			<tr>
				<th data-options="field:'id',width:100,align:'center'">编号</th>
				<th data-options="field:'publishUser',width:100,align:'center'">发布人</th>
				<th data-options="field:'bookName',width:200,align:'center'">书名</th>
				<th data-options="field:'writer',width:100,align:'center'">作者</th>
				<th data-options="field:'出版社',width:100,align:'center'">出版社</th>
				<th data-options="field:'count',width:100,align:'center'" >书籍数量</th>
				<th data-options="field:'phone',width:100,align:'center'" >发布人联系方式</th>
				<th data-options="field:'date',width:100,align:'center'">发布时间</th>
				<th data-options="field:'remark',width:114,align:'center'">备注</th>
				<th data-options="field:'isOrder',width:100,align:'center'">是否被预约</th>
				<th data-options="field:'order',width:100,align:'center'">是否预约</th>
			</tr>
		</thead>
		<tbody >
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
			<tr>
				<td>0000</td>
				<td>wo</td>
				<td>疯狂java讲义</td>
				<td>李刚</td>
				<td>人大出版社</td>
				<td>1</td>
				<td>12345678977</td>
				<td>2014-12</td>
				<td></td>
				<td>否</td>
				<td><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">预约</a></td>
			</tr>
		</tbody>
	</table>
	<div class="easyui-window add_book_win" title="发布漂流图书信息"
		style="width: 600px; height: 460px;"
		data-options="closed:true,iconCls:'icon-add',modal:true,shadow:true,collapsible:false,maximizable:false,minimizable:false";>
		<form id="add_book_form" method="post">
			<table>
				<tbody>
					<tr height="20px">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td width="150px" height="50px"></td>
						<td width="100px" height="50px"><label for="bookName">书名:</label></td>
						<td width="200px" height="50px"><input class="easyui-validatebox" type="text" name="bookName" data-options="required:true,missingMessage:'书名不能为空'" style="width:200px;"/>   </td>
						<td width="150px" height="50px"></td>
					</tr>
					<tr>
						<td width="150px" height="50px"></td>
						<td width="100px" height="50px"><label for="writer">作者:</label></td>
						<td width="200px" height="50px"><input class="easyui-validatebox" type="text" name="writer" data-options="required:true,missingMessage:'作者名不能为空'" style="width:200px;"/>   </td>
						<td width="150px" height="50px"></td>
					</tr>
					<tr>
						<td width="150px" height="50px"></td>
						<td width="100px" height="50px"><label for="publish">出版社:</label></td>
						<td width="200px" height="50px"><input class="easyui-validatebox" type="text" name="writer" data-options="required:true,missingMessage:'出版社不能为空'" style="width:200px;"/></td>
						<td width="150px" height="50px"></td>
					</tr>
					<tr>
						<td width="150px" height="50px"></td>
						<td width="100px" height="50px"><label for="count">数量:</label></td>
						<td width="200px" height="50px"><input  name="count" value="1" class="easyui-numberspinner" style="width:200px;"
       													 required="required" data-options="min:1,max:1000,editable:false">  </td>
						<td width="150px" height="50px"></td>
					</tr>
					<tr>
						<td width="150px" height="50px"></td>
						<td width="100px" height="50px"><label for="phoneNamber">电话号码:</label></td>
						<td width="200px" height="50px"><input class="easyui-validatebox" type="text" name="phoneNamber" data-options="required:true,missingMessage:'电话号码不能为空'" style="width:200px;"/></td>
						<td width="150px" height="50px"></td>
					</tr>
					<tr>
						<td width="150px" height="50px"></td>
						<td width="100px" height="50px"><label for="remark">备注:</label></td>
						<td width="200px" height="50px"><input class="easyui-validatebox" type="text" name="phoneNamber" data-options="required:true,missingMessage:'电话号码不能为空'" style="width:200px;"/></td>
						<td width="150px" height="50px"></td>
					</tr>
					<tr>
						<td width="150px" height="50px"></td>
						<td width="100px" height="50px"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width: 80px">重置</a></td>
						<td width="200px" height="50px" align="right"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width: 80px">发布</a></td>
						<td width="150px" height="50px"></td>
					</tr>
					<tr height="20px">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>

 
