package view.components
{
	import flash.display.Sprite;
	
	import locales.Locale;
	
	import mx.containers.Box;
	import mx.controls.ProgressBar;
	import mx.core.Application;
	import mx.core.FlexGlobals;
	import mx.managers.PopUpManager;
	
	public class Mask extends Box
	{
	
		private static var _mask:Mask;
		
		private var _message:String;
		
		public function Mask()
		{
			super();
		}
		
		/**
		 * Shows a indefined progress bar with the text "msg". If msg is null shows "pleaseWait"
		 */ 
		public static function show(msg:String=null, parent:Sprite=null):Mask
		{
			//closes previous mask
			if(_mask!=null){
				close();
			}
			_mask = new Mask();
			if(msg==null){
				_mask._message = Locale.getInstance().getCurrentLocale().messages.pleaseWait;
			}else{
				_mask._message = msg;
			}
			PopUpManager.addPopUp(_mask, parent||Sprite(FlexGlobals.topLevelApplication), true);
			PopUpManager.centerPopUp(_mask);
			
			return _mask;	
		}
		
		
		public static function close():void 
		{
			PopUpManager.removePopUp(_mask);
		}
		
		override protected function createChildren():void
		{
			super.createChildren();
		
			var pb:ProgressBar = new ProgressBar();
			pb.label = _message||"Please wait...";
			pb.indeterminate = true;
			pb.labelPlacement= 'center';
			pb.setStyle('barColor', uint(0xAEAEAE));
			pb.height = 26;
		
			addChild(pb);				
		}
	}	
}