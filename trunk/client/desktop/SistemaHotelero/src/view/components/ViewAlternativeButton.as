package view.components
{
	import flash.events.MouseEvent;
	
	import locales.Locale;
	
	import model.Alternative;
	import model.AlternativeWrapper;
	
	import mx.collections.ArrayCollection;
	import mx.containers.TitleWindow;
	import mx.controls.Alert;
	import mx.core.Window;
	import mx.managers.PopUpManager;
	
	import spark.components.Application;
	import spark.components.Button;
	
	import utils.WindowsUtils;
	
	public class ViewAlternativeButton extends Button
	{
		private var titleWindow:TitleWindow;
		
		public var alternative:AlternativeWrapper;
		
		
		public function ViewAlternativeButton()
		{
			super();
			this.label = Locale.getInstance().getCurrentLocale().alternative.view;
		}
		
		protected override function clickHandler(event:MouseEvent):void{
			if(alternative!=null){
				var alternativeInCalendar: AlternativeViewInCalendar = new AlternativeViewInCalendar();
				alternativeInCalendar.roomsToShow = new ArrayCollection();
				alternativeInCalendar.roomsToShow.addItem(alternative.alternative.lastRoom);
				alternativeInCalendar.alternative = alternative;
				alternativeInCalendar.addEventListener("closeClicked",titleWindow_close);
				titleWindow = WindowsUtils.openDialog(Locale.getInstance().getCurrentLocale().alternative.singular,alternativeInCalendar);
			}
		}

		private function titleWindow_close(evt:Object):void {
			PopUpManager.removePopUp(titleWindow);
		}
	
	}
}