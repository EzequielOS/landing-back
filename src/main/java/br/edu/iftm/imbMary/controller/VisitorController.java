package br.edu.iftm.imbMary.controller;

import br.edu.iftm.imbMary.domain.Visitor;
import br.edu.iftm.imbMary.repository.VisitorRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class VisitorController extends GenericController<Visitor> {

    public VisitorController(VisitorRepository visitorRepository){
        super(visitorRepository);
    }
}