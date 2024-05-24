package ru.aleksandra0KR.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import ru.aleksandra0KR.dao.Owner;
import ru.aleksandra0KR.dto.OwnerDto;
import ru.aleksandra0KR.dto.OwnerDtoClient;
import ru.aleksandra0KR.exception.PersonDoesntExistException;
import ru.aleksandra0KR.mapper.OwnerMapper;
import ru.aleksandra0KR.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aleksandra0KR.ru.dto.CatDtoMessage;
import ru.aleksandra0KR.ru.dto.OwnerDtoMessage;


@Service
public class OwnerServiceImplementation implements OwnerService {

  final OwnerRepository ownerRepository;

  public OwnerServiceImplementation(OwnerRepository ownerRepository) {
    this.ownerRepository = ownerRepository;
  }

  @Override
  public OwnerDtoClient findPersonByID(long id) {
    Owner owner = ownerRepository.findById(id)
        .orElseThrow(() -> new PersonDoesntExistException(id));
    return OwnerDtoClient.builder()
        .owner_id(owner.getOwner_id())
        .birthday(owner.getBirthday())
        .name(owner.getName())
            .build();
  }

  @Override
  public OwnerDto findPersonByName(String name) {
    var person = ownerRepository.findByName(name);
    if (person == null) {
      throw new PersonDoesntExistException(name);
    }
    return OwnerMapper.asDto(person);
  }

  // TODO
  @RabbitListener(queues = "personAddQueue")
  @Override
  public OwnerDto addPerson(OwnerDto person) {
    Owner dao = ownerRepository.save(OwnerMapper.asDao(person));
    Long generatedId = dao.getOwner_id();
    person.setId(generatedId);
    return person;
  }

  @RabbitListener(queues = "ownerUpdateQueue")
  @Transactional
  @Override
  public void updatePerson(OwnerDto person) {
    Owner owner = ownerRepository.findById(person.getId())
        .orElseThrow(() -> new PersonDoesntExistException(person.getId()));
    owner.setName(person.getName());
    owner.setBirthday(person.getBirthDate());
    ownerRepository.save(owner);
  }

  @RabbitListener(queues = "ownerDeleteQueue")
  @Transactional
  @Override
  public void deletePerson(Long id) {
    Owner owner = ownerRepository.findById(id)
        .orElseThrow(() -> new PersonDoesntExistException(id));
    ownerRepository.delete(owner);
  }



  @RabbitListener(queues = "ownerAddQueue")
  @Transactional
  @Override
  public void addOwner(OwnerDtoMessage ownerDtoMessage) {
    Owner dao = ownerRepository.save(
        Owner.builder()
            .name(ownerDtoMessage.getName())
            .owner_id(ownerDtoMessage.getId())
            .birthday(ownerDtoMessage.getBirthDay())
            .build()
    );
    Long generatedId = dao.getOwner_id();
    ownerDtoMessage.setId(generatedId);
  }
}