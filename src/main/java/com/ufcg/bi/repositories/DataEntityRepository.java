//package com.ufcg.bi.repositories;
//
//import com.ufcg.bi.models.DataEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface DataEntityRepository extends JpaRepository<DataEntity, Long> {
//    // Aqui você pode adicionar métodos personalizados de consulta, se necessário
//    // Exemplo: Método para buscar um DataEntity por ID
//    DataEntity findById(long id);
//
//    // Outros exemplos de métodos personalizados para consultas específicas podem ser adicionados conforme necessário
//    // Por exemplo, consultar distribuições específicas:
//    // Map<String, Map<String, Double>> findGenderDistributionByDataEntityId(Long dataEntityId);
//}
