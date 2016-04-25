package es.javiergarciaescobedo.ejemplorestfulitems;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@WebServlet(name = "RestItems", urlPatterns = {"/RestItems"})
public class RestItems extends HttpServlet {
 
    public static final byte PETICION_GET = 0;    // SELECT
    public static final byte PETICION_POST = 1;   // INSERT
    public static final byte PETICION_PUT = 2;    // UPDATE
    public static final byte PETICION_DELETE = 3; // DELETE
 
    private byte tipoPeticion;
    private Items items = new Items();
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // En caso de que la lista esté vacía, se van a generar algunos objetos
        //  de muestra para las pruebas, que se añadirán a la lista que se 
        //  encuentra dentro del objeto items. Se indica como fecha, la del día actual
        if(items.getItemsList().isEmpty()) {
            items.getItemsList().add(new Item(1, "Primero", 111, Calendar.getInstance().getTime()));
            items.getItemsList().add(new Item(2, "Segundo", 222, Calendar.getInstance().getTime()));
            items.getItemsList().add(new Item(3, "Tercero", 333, Calendar.getInstance().getTime()));
        }
        
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {            
            JAXBContext jaxbContext = JAXBContext.newInstance(Items.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            switch (tipoPeticion) {
                case PETICION_GET:
                    // Convertir a XML el contenido de items, generando el resultado en out
                    jaxbMarshaller.marshal(items, out);
                    break;
                case PETICION_POST:
                    // Obtener la lista de items que se quieren insertar
                    Items newItems = (Items) jaxbUnmarshaller.unmarshal(request.getInputStream());
                    // Recorrer la lista obteniendo cada objeto contenido en ella
                    for(Item item :  newItems.getItemsList()) {
                        // Añadir cada objeto a la lista general
                        items.getItemsList().add(item);
                    }
                    break;
                case PETICION_PUT:
                    // Escribir aquí las accciones para peticiones por PUT
                    break;
                case PETICION_DELETE:
                    // Escribir aquí las accciones para peticiones por DELETE
                    break;
                default:
                    break;
            }
            
        } catch (JAXBException ex) {
            Logger.getLogger(RestItems.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        tipoPeticion = PETICION_GET;
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        tipoPeticion = PETICION_POST;
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>DELETE</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        tipoPeticion = PETICION_DELETE;
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>PUT</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        tipoPeticion = PETICION_PUT;
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
