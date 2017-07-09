package es.cic.taller.ejercicio07;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	
	       
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
                         
        final TextField tfNombre = new TextField();
        tfNombre.setCaption("Nombre:");

        final TextField tfApellidos = new TextField();
        tfApellidos.setCaption("Apellidos:");

        
        DateTimeField tfFechaNac  = new DateTimeField();
        tfFechaNac.setCaption("Fecha: ");
        
        
        
            
  
        Collection<Pais> listaPaises = getListaPaises();
        ComboBox<Pais> cbPais = new ComboBox<>("Select your country", listaPaises);
        cbPais.setPlaceholder("No country selected");
        cbPais.setItemCaptionGenerator(Pais::getNombreCompleto);
 
        // Disallow null selections
        cbPais.setEmptySelectionAllowed(false);
 
       
        
        Button button = new Button("Imprime datos");
        button.addClickListener( e -> {
        	Persona persona=new Persona();
        	persona.setNombre(tfNombre.getValue());
        	persona.setApellidos(tfApellidos.getValue());
        	persona.setFechaNac(tfFechaNac.getValue());
        	
        	
        	persona.setFechaNac(tfFechaNac.getValue());
        	persona.setPais(cbPais.getValue());
        	
        	Notification.show(calcularMensaje(persona),
                       Type.TRAY_NOTIFICATION);        
	
        });
        
        Button button2 = new Button("Cambia");
        button2.addClickListener( e -> {
        	button.setVisible(!button.isVisible());
//        	if (button.isVisible()) {
//        		button.setVisible(false);
//        	} else {
//        		button.setVisible(true);
//        	}
        	
        });
        
        layout.addComponents(tfNombre, tfApellidos, tfFechaNac,  cbPais, button,button2);
        
        setContent(layout);
    }

	private String calcularMensaje(Persona persona) {
		LocalDateTime fecha =persona.getFechaNac();
		
		String sFecha="Debe incluir una fecha";
		if (fecha!=null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yy");
			sFecha = fecha.format(dtf);
			}
		
		String mensaje=String.format("Mis datos %s %s, estamos a %s en %s", persona.getNombre(), persona.getApellidos(),sFecha, persona.getPais());
		return mensaje;
	}

	private Collection<Pais> getListaPaises(){
		List<Pais> listaPaises = new ArrayList<>();
		
		Pais pais1=new Pais();
		pais1.setNombreCompleto("España");
		
		listaPaises.add(pais1);
		
		pais1=new Pais();
		pais1.setNombreCompleto("Alemania");
		
		listaPaises.add(pais1);
		
		pais1=new Pais();
		pais1.setNombreCompleto("Francia");
		
		listaPaises.add(pais1);
		
		return listaPaises;
		
	}

	
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
