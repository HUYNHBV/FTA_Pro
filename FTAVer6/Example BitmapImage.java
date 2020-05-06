public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.gridview_item, null);
		// Locate the TextView in gridview_item.xml
		TextView text = (TextView) vi.findViewById(R.id.text);
		// Locate the ImageView in gridview_item.xml
		ImageView image = (ImageView) vi.findViewById(R.id.image);
 
		// Set file name to the TextView followed by the position
		text.setText(filename[position]);
 
		// Decode the filepath with BitmapFactory followed by the position
		Bitmap bmp = BitmapFactory.decodeFile(filepath[position]);
 
		// Set the decoded bitmap into ImageView
		image.setImageBitmap(bmp);
		return vi;
	}