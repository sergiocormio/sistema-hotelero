package utils
{
	import flash.display.DisplayObject;
	
	import mx.containers.TitleWindow;
	import mx.core.FlexGlobals;
	import mx.managers.PopUpManager;
	
	import spark.components.ComboBox;
	import spark.components.DropDownList;
	

	public class WindowsUtils
	{
		public function WindowsUtils()
		{
		}
		
		public static function openDialog(title:String,componentInside:DisplayObject,parent:DisplayObject=null, minWidth:Number=-1, minHeight:Number=-1):TitleWindow{
			var titleWindow:TitleWindow = new TitleWindow();
			titleWindow.title = title;
			titleWindow.showCloseButton = false;
			titleWindow.addChild(componentInside);
			
			if(minWidth != -1){
				titleWindow.minWidth = minWidth;
			}
			if(minHeight != -1){
				titleWindow.minHeight = minHeight;
			}
			
			//if there is no Parent, display in the middle of the screen
			if(parent == null){
				parent = FlexGlobals.topLevelApplication as DisplayObject;
			}
			PopUpManager.addPopUp(titleWindow, parent, true);
			PopUpManager.centerPopUp(titleWindow);
			return titleWindow;
		}
		
		public static function autoSelectComboBox(combo:ComboBox,obj:Object,idFieldName:String="id"):void{
			if(obj == null){
				return;
			}

			var found:Boolean = false;
			var i:int = 0;	
			for each (var objAux:Object in combo.dataProvider){
				if(objAux[idFieldName].toString() == obj[idFieldName].toString()){
					combo.selectedItem = objAux;
					combo.selectedIndex = i;
					found = true;
					break;
				}
				i++;
			}
			if(!found){
				combo.selectedItem = obj;
			}
		}
		
		public static function autoSelectDropDownList(list:DropDownList,id:String,idFieldName:String="id"):void{
			if(id == null){
				return;
			}
			
			var found:Boolean = false;
			var i:int = 0;	
			for each (var objAux:Object in list.dataProvider){
				if(objAux[idFieldName] == id){
					list.selectedItem = objAux;
					list.selectedIndex = i;
					found = true;
					break;
				}
				i++;
			}
			if(!found){
				list.selectedItem = id;
			}
		}
		
	}
}