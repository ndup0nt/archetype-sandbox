/**
 * Copyright (C) 2000 - 2012 Silverpeas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * As a special exception to the terms and conditions of version 3.0 of
 * the GPL, you may redistribute this Program in connection with Free/Libre
 * Open Source Software ("FLOSS") applications as described in Silverpeas's
 * FLOSS exception.  You should have received a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "http://www.silverpeas.org/docs/core/legal/floss_exception.html"
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.silverpeas.components.oosphere.blankCmp.blankStuff;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "sc_blankCmp_blankStuff")
public class BlankStuff{
    @Id
    @SequenceGenerator(name = "BlankStuffGen", sequenceName = "sc_blankCmp_blankStuff_seq",
            allocationSize = 1)
    @GeneratedValue(generator = "BlankStuffGen", strategy = GenerationType.SEQUENCE)
    @JsonProperty
    protected Integer id;
    @NotEmpty
    private String label;

	public BlankStuff(){}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj){
			return true;
        }
		if (!(obj instanceof BlankStuff)){
			return false;
        }
		BlankStuff other = (BlankStuff) obj;
		if (label == null) {
			if (other.label != null){
				return false;
            }
		} else if (!label.equals(other.label))
			return false;
		return true;
	}
}
