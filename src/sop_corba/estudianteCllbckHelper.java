package sop_corba;


/**
* sop_corba/estudianteCllbckHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from requerimiento.idl
* domingo 7 de junio de 2015 12:19:24 PM COT
*/

abstract public class estudianteCllbckHelper
{
  private static String  _id = "IDL:sop_corba/estudianteCllbck:1.0";

  public static void insert (org.omg.CORBA.Any a, sop_corba.estudianteCllbck that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static sop_corba.estudianteCllbck extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (sop_corba.estudianteCllbckHelper.id (), "estudianteCllbck");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static sop_corba.estudianteCllbck read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_estudianteCllbckStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, sop_corba.estudianteCllbck value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static sop_corba.estudianteCllbck narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof sop_corba.estudianteCllbck)
      return (sop_corba.estudianteCllbck)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      sop_corba._estudianteCllbckStub stub = new sop_corba._estudianteCllbckStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static sop_corba.estudianteCllbck unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof sop_corba.estudianteCllbck)
      return (sop_corba.estudianteCllbck)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      sop_corba._estudianteCllbckStub stub = new sop_corba._estudianteCllbckStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
