package utils
{
	import flash.display.DisplayObject;
	
	import mx.containers.TitleWindow;
	import mx.managers.PopUpManager;
	
	import spark.components.ComboBox;
	

	public class WindowsUtils
	{
		public function WindowsUtils()
		{
		}
		
		public static function openDialog(title:String,componentInside:DisplayObject,parent:DisplayObject=null):TitleWindow{
			var titleWindow:TitleWindow = new TitleWindow();
			titleWindow.title = title;
			titleWindow.showCloseButton = false;
			titleWindow.addChild(componentInside);
			
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
				if(objAux[idFieldName] == obj[idFieldName]){
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
	}
}