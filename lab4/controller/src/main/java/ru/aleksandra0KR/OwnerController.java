package ru.aleksandra0KR;

import jakarta.validation.Valid;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aleksandra0KR.dto.CatDto;
import ru.aleksandra0KR.dto.OwnerDto;
import ru.aleksandra0KR.service.OwnerService;

@RestController
@RequestMapping("/owner")
@Validated
public class OwnerController {

  private final OwnerService ownerService;

  @Autowired
  public OwnerController(OwnerService ownerService) {
    this.ownerService = ownerService;
  }

  @GetMapping("/{id}")
  public OwnerDto findPersonById(@PathVariable("id") Long id) {
    return ownerService.findPersonByID(id);
  }

  @GetMapping("/name/{name}")
  public OwnerDto findPersonByName(@PathVariable("name") String name) {
    return ownerService.findPersonByName(name);
  }

  @GetMapping("/{id}/cats")
  public ResponseEntity<List<CatDto>> findAllCats(@PathVariable("id") Long id) {
    List<CatDto> cats = ownerService.findAllCats(id);

    if (cats.isEmpty()) {
      return ResponseEntity.ok().body(Collections.emptyList());
    } else {
      return ResponseEntity.ok(cats);
    }
  }

  @DeleteMapping("/{id}")
  public void deletePerson(@PathVariable("id") Long id) {
    ownerService.deletePerson(id);
  }

  @PutMapping
  public void updatePerson(@Valid @RequestBody OwnerDto person) {
    ownerService.updatePerson(person);
  }
}