package view.components
{
	import flash.events.MouseEvent;
	
	import locales.Locale;
	
	import model.Alternative;
	import model.AlternativeWrapper;
	
	import mx.controls.Alert;
	
	import spark.components.Button;
	
	public class ViewAlternativeButton extends Button
	{
		public var alternative:AlternativeWrapper;
		
		public function ViewAlternativeButton()
		{
			super();
			this.label = Locale.getInstance().getCurrentLocale().alternative.view;
		}
		
		protected override function clickHandler(event:MouseEvent):void{
			if(alternative!=null){
				Alert.show("Base Price: " + alternative.alternative.budget.basePrice);
			}
		}
	
	}
}