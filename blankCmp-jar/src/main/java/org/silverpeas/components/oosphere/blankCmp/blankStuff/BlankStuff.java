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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BlankStuff))
			return false;
		BlankStuff other = (BlankStuff) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}
}
