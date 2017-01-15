package be.jegoossens.es.graph.poc;


import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

interface UserRepository extends ElasticsearchCrudRepository<User, String> {
}
