package com.boardcamp.api.Unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.boardcamp.api.dtos.customersDTO;
import com.boardcamp.api.exceptions.ConflictException;
import com.boardcamp.api.exceptions.EmptyFieldException;
import com.boardcamp.api.exceptions.InvalidCpfException;
import com.boardcamp.api.exceptions.ResourceNotFoundException;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.services.CustomersService;

@ExtendWith(MockitoExtension.class)
class CustomerUnitTests {

	@InjectMocks
	private CustomersService customersService;

	@Mock
	private CustomerRepository customerRepository;

	@Test
	void givenRepeatedCpf_WhenCreatingCustomer_thenThrowsError() {
		customersDTO customer = new customersDTO(
				"Joao",
				"22111133331",
				"11111111111");

		when(customerRepository.existsByCpf(anyString()))
				.thenReturn(true);

		ConflictException ex = assertThrows(
				ConflictException.class,
				() -> customersService.postCustomer(customer));

		verify(customerRepository, never()).save(any());
		assertNotNull(ex);
		assertEquals("This cpf already exists", ex.getMessage());
	}

	@Test
	void givenNullCpf_WhenCreatingCustomer_thenThrowsInvalidCpf() {
		customersDTO customer = new customersDTO(
				"Joao",
				"22111133331",
				"11111111111000");

		InvalidCpfException ex = assertThrows(
				InvalidCpfException.class,
				() -> customersService.postCustomer(customer));

		assertEquals("Cpf must have 11 digits", ex.getMessage());
	}

	@Test
	void givenNullPhone_WhenCreatingCustomer_thenThrowsInvalidNumber() {
		customersDTO customer = new customersDTO(
				"Joao",
				"221",
				"11111111111");

		InvalidCpfException ex = assertThrows(
				InvalidCpfException.class,
				() -> customersService.postCustomer(customer));

		assertEquals("Phone must have 10 or 11 digits", ex.getMessage());
	}

	@Test
	void givenEmptyFieldName_WhenCreatingCustomer_thenThrowsInvalidName() {
		customersDTO customer = new customersDTO(
				"",
				"221",
				"11111111111");

		EmptyFieldException ex = assertThrows(
				EmptyFieldException.class,
				() -> customersService.postCustomer(customer));

		assertEquals("Name must not be null or empty", ex.getMessage());
	}

	@Test
	void givenWrongUserId_whenGetcustomerById_thenthrowsError() {
		Long id = 0L;

		ResourceNotFoundException ex = assertThrows(
				ResourceNotFoundException.class,
				() -> customersService.getCustomerById(id));

		assertEquals("Customer not found", ex.getMessage());
	}

}
