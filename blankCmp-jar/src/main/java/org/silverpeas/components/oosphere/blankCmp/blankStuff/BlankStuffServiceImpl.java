package org.silverpeas.components.oosphere.blankCmp.blankStuff;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.transaction.annotation.Transactional;

@Named
@Singleton
public class BlankStuffServiceImpl implements BlankStuffService {
	@Inject private BlankStuffRepository blankStuffRepository;
	
	@Override
	@Transactional //this annotation is especially useful for handling lazy loading issues (for instance if you have to deal with an existing entity and a lazy initialized collection in the same method)
	public BlankStuff createNewBlankStuff(BlankStuff blankStuff) {
		return blankStuffRepository.saveAndFlush(blankStuff);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BlankStuff> getAllBlankStuffs() {
		return blankStuffRepository.findAll();
	}

}
