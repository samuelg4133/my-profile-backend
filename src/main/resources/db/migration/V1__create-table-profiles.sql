CREATE TABLE IF NOT EXISTS profiles (
  id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
  document VARCHAR(11) NOT NULL,
  email VARCHAR(100) NOT NULL,
  name VARCHAR(191) NOT NULL,
  UNIQUE (document),
  UNIQUE (email)
);