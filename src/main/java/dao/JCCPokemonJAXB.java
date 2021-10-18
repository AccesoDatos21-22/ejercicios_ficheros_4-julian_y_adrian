package dao;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import modelo.JCCPokemon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JCCPokemonJAXB implements JCCPokemonDAO {
	JAXBContext jaxbContext;
	private final String FICHEROJCC="JAXB.xml";

	@Override
	public JCCPokemon leer() {
		JCCPokemon p1 = null;
		try {
			jaxbContext = JAXBContext.newInstance(JCCPokemon.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			p1 = (JCCPokemon) unmarshaller.unmarshal(Files.newInputStream(Path.of(FICHEROJCC)));
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}

		return p1;
	}

	@Override
	public boolean guardar(JCCPokemon pokemones) {
		try {
			jaxbContext = JAXBContext.newInstance(modelo.JCCPokemon.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(pokemones, Files.newOutputStream(Path.of(FICHEROJCC)));
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
		return true;
	}

}
