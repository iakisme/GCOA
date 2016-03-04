package tool;

import java.io.IOException;

import com.qylm.dto.engineeringMaterials.MaterialsGrantCreateDto;
import com.qylm.dto.engineeringMaterials.MaterialsGrantCreateProjectDto;
import com.qylm.entity.EngineeringProject;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.MaterialsGrant;
import com.qylm.tool.DxoCreator;

public class DxoCreatorExe {

	public static void main(String[] args) {
		try {
			DxoCreator.create(MaterialsGrantCreateProjectDto.class, EngineeringProject.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
