
package controlador;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

import modelo.Producto;


@WebServlet(name = "GenerarPedido", urlPatterns = {"/GenerarPedido"})
public class GenerarPedido extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DocumentException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String[] codigos = request.getParameterValues("cod");
        String[] nombres = request.getParameterValues("nom");
        String[] precios = request.getParameterValues("precio");
        String[] cantidades = request.getParameterValues("cant");
        
        String usuario = (String) request.getSession().getAttribute("user");
     
        // Se crea el documento
        Document documento = new Document();

        // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
        FileOutputStream ficheroPdf = new FileOutputStream("C:\\upload\\fichero.pdf");

        try {
            // Se asocia el documento al OutputStream y se indica que el espaciado entre
            // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
            PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
        } catch (DocumentException ex) {
            Logger.getLogger(GenerarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Se abre el documento.
        documento.open();
              
        String pedido = "Su pedido " + usuario;
        documento.add(new Paragraph(pedido,
				FontFactory.getFont("arial",   // fuente
				22,                            // tamaño
				Font.ITALIC,                   // estilo
				BaseColor.CYAN)));             // color       
        try
        {
            Image foto = Image.getInstance("C:\\upload\\carrito.png");
            foto.scaleToFit(100, 100);
            foto.setAlignment(Chunk.ALIGN_MIDDLE);
            documento.add(foto);
        }
        catch ( DocumentException | IOException e )
        {
        }
       
        PdfPTable tabla = new PdfPTable(4);
        tabla.addCell("Código"); tabla.addCell("Nombre");
        tabla.addCell("Precio"); tabla.addCell("Cantidad");
        Double total = 0.0;
        for (int i=0; i<codigos.length; i++) {
            tabla.addCell(codigos[i]);
            tabla.addCell(nombres[i]);
            tabla.addCell(precios[i]);
            tabla.addCell(cantidades[i]);
            total +=  Double.parseDouble(precios[i]); 
        }
        
        documento.add(tabla);
        
        String Total = total.toString() + "€";
        
        documento.add(new Paragraph("Total: " + Total,
				FontFactory.getFont("arial",   // fuente
				22,                            // tamaño
				Font.ITALIC,                   // estilo
				BaseColor.BLUE)));             // color       
        
        documento.add(new Paragraph("  ", 
				FontFactory.getFont("arial",   // fuente
				50,                            // tamaño
				Font.ITALIC,                   // estilo
				BaseColor.WHITE)));             // color       
        
        
        
        
        documento.add(new Paragraph("Se entregará en C/Alcalá 321 28047 Madrid", 
				FontFactory.getFont("arial",   // fuente
				12,                            // tamaño
				Font.ITALIC,                   // estilo
				BaseColor.ORANGE)));             // color       
        
        
        
        documento.close();
        
        File path = new File("C:\\upload\\fichero.pdf");
        Desktop.getDesktop().open(path);
        
        // Redirigir a la vista lista de productos para verficar la operación.
        RequestDispatcher rd = request.getRequestDispatcher("/mostrarPedidoView.jsp");     
        rd.forward(request, response);  
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
        try {
            processRequest(request, response);
        } catch (DocumentException ex) {
            Logger.getLogger(GenerarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (DocumentException ex) {
            Logger.getLogger(GenerarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
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
