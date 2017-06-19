package at.jku.ce.cep.beans;

import static at.jku.ce.cep.common.Constants.MAX_HISTORY;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.StringUtils;

import at.jku.ce.cep.CepUI;
import at.jku.ce.cep.dao.DaoServiceRegistry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class HistorizableEntity extends VersionableEntity<HistoryPK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7891004754802807555L;
	
	@EmbeddedId
	private HistoryPK id;
	
	@Override
	public boolean isNew() {
		return super.isNew() || StringUtils.isBlank(getId().getId());
	}
	
	@Override
	public void preCreate() {
		setId(new HistoryPK((String) DaoServiceRegistry.provideUuid(), MAX_HISTORY));
		if(getHeader().getCreatedDate() == null){
			Date now = new Date();
			getHeader().setCreatedDate(now);
		}
		getHeader().setCreatedBy(CepUI.getUser());
		super.preCreate();
	}

	@Override
	public void preUpdate() {
		Date now = new Date();
		getHeader().setModifiedDate(now);
		getHeader().setModifiedBy(CepUI.getUser());
		super.preUpdate();
	}

	@Override
	public void preDelete() {
		Date now = new Date();
		getHeader().setFlaggedDeletedDate(now);
		getHeader().setFlaggedDeletedBy(CepUI.getUser());
		super.preDelete();
	}
}
