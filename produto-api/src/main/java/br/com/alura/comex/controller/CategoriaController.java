package br.com.alura.comex.controller;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.comex.dto.CategoriaRequest;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired 
    private CategoriaService categoriaService;
    
    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        return new ResponseEntity<>(categoriaService.getAll(),HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Categoria> cadastrarCategoria(
        @RequestBody @Valid CategoriaRequest request, 
        UriComponentsBuilder uriBuilder) {
        
        Categoria novaCategoria = categoriaService.cadastrar(request);
        
        KafkaProducer kafkaProducer = new KafkaProducer<String, String>(properties());

        ProducerRecord producerRecord = new ProducerRecord<>("PRODUTO_CATEGORIA_CRIADA", request.nome());

        try {
            kafkaProducer.send(producerRecord).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(novaCategoria,HttpStatus.CREATED);
    }
    private Properties properties() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
