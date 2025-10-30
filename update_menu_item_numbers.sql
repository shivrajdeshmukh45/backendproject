-- SQL script to update existing menu items with sequential numbers within each restaurant

-- Add the new column if it doesn't exist
ALTER TABLE menu_items ADD COLUMN IF NOT EXISTS menu_item_number INTEGER;

-- Update existing menu items with sequential numbers within each restaurant
WITH numbered_items AS (
    SELECT 
        id,
        restaurant_id,
        ROW_NUMBER() OVER (PARTITION BY restaurant_id ORDER BY id) as new_number
    FROM menu_items
)
UPDATE menu_items 
SET menu_item_number = numbered_items.new_number
FROM numbered_items 
WHERE menu_items.id = numbered_items.id;

-- Verify the update
SELECT restaurant_id, id, menu_item_number, name 
FROM menu_items 
ORDER BY restaurant_id, menu_item_number;