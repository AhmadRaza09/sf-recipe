package ahmad.recipe.sfrecipe.service;

import ahmad.recipe.sfrecipe.commands.UnitOfMeasureCommand;
import ahmad.recipe.sfrecipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import ahmad.recipe.sfrecipe.repostories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
                                    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
        Set<UnitOfMeasureCommand> unitOfMeasureCommand = new HashSet<>();

        unitOfMeasureRepository.findAll().forEach(unitOfMeasure -> {
            unitOfMeasureCommand.add(unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure));
        });

        return unitOfMeasureCommand;
    }
}
