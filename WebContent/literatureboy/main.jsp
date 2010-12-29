<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="/common/taglib.inc.jsp"%>
<link rel="stylesheet" type="text/css" href="/common/css/jquery-ui-1.7.3.custom.css" />


		<script type="text/javascript">
			$(function(){
	
				// Dialog			
				$('#dialog').dialog({
					autoOpen: false,
					width: 600,
					buttons: {
						"닫음": function() { 
							$(this).dialog("close"); 
						} 
					}
				});

				// Dialog Link
				$('#dialog_link').click(function(){
					$('#dialog').dialog('open');
					return false;
				});

				//hover states on the static widgets
				$('#dialog_link, ul#icons li').hover(
					function() { $(this).addClass('ui-state-hover'); }, 
					function() { $(this).removeClass('ui-state-hover'); }
				);

				
			});
		</script>
<p><a href="#" id="dialog_link" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-newwin"></span>Open Dialog</a></p>

<div id="dialog" title="Dialog Title">
	<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
</div>