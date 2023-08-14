ALTER TABLE profiles 
ADD COLUMN created_at TIMESTAMP(0) NOT NULL DEFAULT current_timestamp(0),
ADD COLUMN updated_at TIMESTAMP(0) NOT NULL DEFAULT current_timestamp(0);
