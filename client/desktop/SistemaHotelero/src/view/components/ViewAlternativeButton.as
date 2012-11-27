package view.components
{
	import flash.events.MouseEvent;
	
	import locales.Locale;
	
	import model.AlternativeWrapper;
	
	import mx.collections.ArrayCollection;
	import mx.containers.TitleWindow;
	import mx.managers.PopUpManager;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import services.OccupationService;
	import services.crud.RoomService;
	
	import spark.components.Button;
	
	import utils.WindowsUtils;
	import utils.log.DebugLog;
	
	public class ViewAlternativeButton extends Button
	{
		private var titleWindow:TitleWindow;
		
		public var alternative:AlternativeWrapper;
		
		private var alternativeInCalendar:AlternativeViewInCalendar;
		
		
		public function ViewAlternativeButton()
		{
			super();
			this.label = Locale.getInstance().getCurrentLocale().alternative.view;
		}
		
		protected override function clickHandler(event:MouseEvent):void{
			if(alternative!=null){
				alternativeInCalendar = new AlternativeViewInCalendar();
//				alternativeInCalendar.roomsToShow = alternative.involvedRooms;
				alternativeInCalendar.alternative = alternative;
				alternativeInCalendar.addEventListener("closeClicked",titleWindow_close);
				titleWindow = WindowsUtils.openDialog(Locale.getInstance().getCurrentLocale().alternative.alternativeView,alternativeInCalendar);
			}
		}
		
		private function titleWindow_close(evt:Object):void {
			PopUpManager.removePopUp(titleWindow);
		}
		
	}
}