/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.flywaydb.core.Flyway;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
/**
 * Aplica as migrations pendentes (src/main/resources/db/migration).
 * Deve ser chamada ANTES de qualquer tela abrir e antes de o JPA/Hibernate
 * ser iniciado, porque com hbm2ddl.auto=validate o Hibernate confere se as
 * tabelas já existem.
 *
 * A URL, o usuário e a senha são lidos do próprio persistence.xml, para que
 * as credenciais continuem em um único lugar (fora do repositório).
 */
public final class MigrationRunner {
 
    private static final String PERSISTENCE_XML = "META-INF/persistence.xml";
 
    private MigrationRunner() { }
 
    public static void migrar() {
        Map<String, String> props = lerPropriedades();
 
        Flyway flyway = Flyway.configure()
                .dataSource(
                        props.get("jakarta.persistence.jdbc.url"),
                        props.get("jakarta.persistence.jdbc.user"),
                        props.get("jakarta.persistence.jdbc.password"))
                .load();
 
        // confere a flyway_schema_history, aplica só o que falta e devolve o controle
        flyway.migrate();
    }
 
    private static Map<String, String> lerPropriedades() {
        try (InputStream in = MigrationRunner.class.getClassLoader()
                .getResourceAsStream(PERSISTENCE_XML)) {
            if (in == null) {
                throw new IllegalStateException(PERSISTENCE_XML + " não encontrado no classpath.");
            }
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            fabrica.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            Document doc = fabrica.newDocumentBuilder().parse(in);
 
            Map<String, String> props = new HashMap<>();
            NodeList nos = doc.getElementsByTagName("property");
            for (int i = 0; i < nos.getLength(); i++) {
                Element p = (Element) nos.item(i);
                props.put(p.getAttribute("name"), p.getAttribute("value"));
            }
            return props;
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new IllegalStateException("Não foi possível ler " + PERSISTENCE_XML, e);
        }
    }
}
 