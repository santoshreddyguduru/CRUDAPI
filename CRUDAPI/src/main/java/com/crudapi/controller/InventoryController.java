package com.crudapi.controller;

import com.crudapi.entity.InventoryItem;
import com.crudapi.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
		@Autowired
		private InventoryItemRepository inventoryItemRepository;

		@GetMapping
		public List<InventoryItem> getAllItems() {
				return inventoryItemRepository.findAll();
		}

		@GetMapping("/{id}")
		public InventoryItem getItemById(@PathVariable Long id) {
				return inventoryItemRepository.findById(id).orElse(null);
		}

		@PostMapping
		public InventoryItem createItem(@RequestBody InventoryItem item) {
				return inventoryItemRepository.save(item);
		}

		@PutMapping("/{id}")
		public InventoryItem updateItem(@PathVariable Long id, @RequestBody InventoryItem updatedItem) {
				InventoryItem item = inventoryItemRepository.findById(id).orElse(null);
				if (item != null) {
						item.setName(updatedItem.getName());
						item.setQuantity(updatedItem.getQuantity());
						return inventoryItemRepository.save(item);
				}
				return null;
		}

		@DeleteMapping("/{id}")
		public void deleteItem(@PathVariable Long id) {
				inventoryItemRepository.deleteById(id);
		}
}

