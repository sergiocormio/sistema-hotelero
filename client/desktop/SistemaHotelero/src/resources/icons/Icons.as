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
		
		//------- Flag Icons -----------
		[Bindable]
		[Embed(source='resources/icons/Argentina Flag 24x24.png')]
		public static var es_AR:Class;
		
		[Bindable]
		[Embed(source='resources/icons/Brazil Flag 24x24.png')]
		public static var pt_BR:Class;
		
		[Bindable]
		[Embed(source='resources/icons/United States Flag 24x24.png')]
		public static var en_US:Class;
		
		//-------- Main Tabs Icon ----------
		[Bindable]
		[Embed(source='resources/icons/users_two_48.png')]
		public static var customers:Class;
		
		public function Icons()
		{
		}
		
	}
}