package com.tecsup.petclinic.mapper;

import com.tecsup.petclinic.domain.OwnerTO;
import com.tecsup.petclinic.entities.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface OwnerMapper {

  OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);

  // Mapping from OwnerTO to Owner
  Owner toOwner(OwnerTO ownerTO);

  // Mapping from Owner to OwnerTO
  OwnerTO toOwnerTO(Owner owner);

  // Mapping lists
  List<OwnerTO> toOwnerTOList(List<Owner> ownerList);

  List<Owner> toOwnerList(List<OwnerTO> ownerTOList);
}
