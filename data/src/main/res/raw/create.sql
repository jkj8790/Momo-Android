CREATE TABLE posts (
    id INTEGER PRIMARY KEY,
    pin_id INTEGER,
    photo_url TEXT,
    description TEXT,
    created_at INTEGER
)

CREATE TABLE pin (
    id INTEGER PRIMARY KEY,
    name TEXT,
    pin_label INTEGER,
    created_at INTEGER,
    author_id INTEGER,
    author_name TEXT,
    map_id INTEGER
)

CREATE TABLE pin_posts (
    pin_id INTEGER,
    post_id INTEGER
)

CREATE TABLE map (
    id INTEGER PRIMARY KEY,
    name TEXT,
    description TEXT,
    is_private INTEGER,
    author_id INTEGER,
    created_at INTEGER
)

CREATE TABLE map_pins (
    map_id INTEGER,
    pin_id INTEGER
)
