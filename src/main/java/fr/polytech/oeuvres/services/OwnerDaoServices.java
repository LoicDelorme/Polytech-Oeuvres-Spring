package fr.polytech.oeuvres.services;

import fr.polytech.oeuvres.entities.Owner;

/**
 * This class represents an owner DAO services.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class OwnerDaoServices extends AbstractDaoServices<Owner> {

	/**
	 * Create an owner DAO services.
	 */
	public OwnerDaoServices() {
		super(Owner.class);
	}
}