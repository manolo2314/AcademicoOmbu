/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import java.io.*;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.primefaces.util.Base64;


/**
 *
 * @author pabloromero
 */
@FacesConverter(value = "GenericConverter")
public class GenericConverter implements Converter, Serializable{
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        try {
            byte[] data = Base64.decode(value);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            Object o = ois.readObject();
            ois.close();
            return o;
        } catch (Exception e) {
           
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(value);
            oos.close();

            return Base64.encodeToString(baos.toByteArray(), true);          
        } catch (IOException e) {
            return "Not a valid theme";
        }
    }
}
