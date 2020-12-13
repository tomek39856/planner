package com.github.tomek39856.planner.boundary;

import com.github.tomek39856.planner.control.ChargeRegisterUseCase;
import com.github.tomek39856.planner.control.CreateRegisterUseCase;
import com.github.tomek39856.planner.control.GetRegistersUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final GetRegistersUseCase getRegistersUseCase;
    private final CreateRegisterUseCase createRegisterUseCase;
    private final ChargeRegisterUseCase chargeRegisterUseCase;

    public RegisterController(GetRegistersUseCase getRegistersUseCase, CreateRegisterUseCase createRegisterUseCase, ChargeRegisterUseCase chargeRegisterUseCase) {
        this.getRegistersUseCase = getRegistersUseCase;
        this.createRegisterUseCase = createRegisterUseCase;
        this.chargeRegisterUseCase = chargeRegisterUseCase;
    }

    @GetMapping
    public List<RegisterApi> findAll() {
        return getRegistersUseCase.execute();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addRegister(@RequestBody @Valid CreateRegisterApi createRegisterApi) {
        createRegisterUseCase.execute(createRegisterApi);
    }

    @PostMapping("/{id}/supply")
    @ResponseStatus(HttpStatus.CREATED)
    public void chargeRegister(@PathVariable("id") String id, @RequestBody @Valid CreateSupplyApi createSupplyApi) {
        chargeRegisterUseCase.charge(id, createSupplyApi);
    }
}
