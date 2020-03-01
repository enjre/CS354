/*
@author: Andre Maldonado
@class: CS354 LA5

*/
using System;
public class BinaryCounter : Label, EventHandler
{

	private int count;

	public BinaryCounter(int count) : base(Integer.toBinaryString(count))
	{
		this.count = count;
	}

	public event EventHandler actionPerformed;
	public virtual void OnActionPerformed(EventArgs e)
	{
		Text = Integer.toBinaryString(++count);
	}

}
