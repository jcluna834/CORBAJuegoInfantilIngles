package sop_corba;

/**
* sop_corba/estudianteCllbckHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from requerimiento.idl
* domingo 7 de junio de 2015 12:19:24 PM COT
*/

public final class estudianteCllbckHolder implements org.omg.CORBA.portable.Streamable
{
  public sop_corba.estudianteCllbck value = null;

  public estudianteCllbckHolder ()
  {
  }

  public estudianteCllbckHolder (sop_corba.estudianteCllbck initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = sop_corba.estudianteCllbckHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    sop_corba.estudianteCllbckHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return sop_corba.estudianteCllbckHelper.type ();
  }

}
