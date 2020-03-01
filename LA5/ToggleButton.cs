/*
@author: Andre Maldonado
@class: CS354 LA5

*/
using System;

public class ToggleButton : Button, EventHandler
{

	private string label1, label2;

	public ToggleButton(string label1, string label2) : base(label1)
	{
		this.label1 = label1;
		this.label2 = label2;
		this.actionPerformed += OnActionPerformed;
	}

	public EventHandler actionPerformed;
	public virtual void OnActionPerformed(EventArgs e)
	{
		string s = label1;
		label1 = label2;
		label2 = s;
		Text = label1;
	}

}
