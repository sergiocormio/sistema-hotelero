package resources.icons
{
	public class Icons
	{
		//------- CRUD Icons -----------
		[Bindable]
		[Embed(source='resources/icons/table_refresh.png')]
		public static var refresh:Class;
		
		[Bindable]
		[Embed(source='resources/icons/add.png')]
		public static var add:Class;
		
		[Bindable]
		[Embed(source='resources/icons/pencil.png')]
		public static var modify:Class;
		
		[Bindable]
		[Embed(source='resources/icons/delete.png')]
		public static var remove:Class;
		
		[Bindable]
		[Embed(source='resources/icons/disk.png')]
		public static var save:Class;
		
		[Bindable]
		[Embed(source='resources/icons/cancel.png')]
		public static var cancel:Class;
		
		public function Icons()
		{
		}
		
	}
}