package org.hib.sample;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class SequencedObject {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqgen")
    @Column(name = "id")
    private int id;
}