package view.components
{
	import flash.events.MouseEvent;
	
	import locales.Locale;
	
	import model.AlternativeWrapper;
	import model.Promotion;
	
	import mx.containers.TitleWindow;
	import mx.controls.Alert;
	import mx.managers.PopUpManager;
	
	import spark.components.Button;
	
	import utils.WindowsUtils;

	public class ViewPromotionButton extends Button
	{
		
		private var titleWindow:TitleWindow;
		
		public var promotion:Promotion;
		
		public function ViewPromotionButton()
		{
			super();
			this.label = Locale.getInstance().getCurrentLocale().alternative.view;
		}
		
		
		protected override function clickHandler(event:MouseEvent):void{
			if(promotion!=null){
				//TODO: Show a nice window with promotion information
				Alert.show(promotion.name + " - " + promotion.description);
			}
		}
		
		private function titleWindow_close(evt:Object):void {
			PopUpManager.removePopUp(titleWindow);
		}
		
	}
}