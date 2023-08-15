CREATE TABLE IF NOT EXISTS users (
  id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
  email VARCHAR(100) NOT NULL,
  name VARCHAR(191) NOT NULL,
  password VARCHAR(255) NOT NULL,
  is_enabled BOOLEAN DEFAULT TRUE NOT NULL,
  created_at TIMESTAMP(0) NOT NULL DEFAULT current_timestamp(0),
  updated_at TIMESTAMP(0) NOT NULL DEFAULT current_timestamp(0),
  UNIQUE (email)
);

CREATE TRIGGER trigger_update_users_updated_at
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE FUNCTION updated_at();