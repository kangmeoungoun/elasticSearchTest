package com.ga.jpatest.elasticsearch.config;

import com.ga.jpatest.elasticsearch.repository.search.KnowSearchRepository;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;

@EnableElasticsearchRepositories(basePackageClasses = {KnowSearchRepository.class})
@Configuration
public class ElasticSearchConfig {

    @Bean
    public Client client() throws Exception {
        Settings settings = Settings.builder().put("cluster.name", "docker-cluster").build();
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.200.159"), 9300));
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }

}
