import os
import fnmatch

def generate_structure(root_dir=".", output_file="structure_tree.md", ignore_dirs=None, ignore_files=None):
    if ignore_dirs is None:
        ignore_dirs = {".git", ".idea", "target", "frontend/node_modules", ".gradle"}

    if ignore_files is None:
        ignore_files = {"*.log"}

    def build_tree(dir_path, prefix=""):
        entries = [entry for entry in os.listdir(dir_path)
                   if entry not in ignore_dirs and
                   not any(fnmatch.fnmatch(entry, pattern) for pattern in ignore_files)]
        entries.sort()
        tree_lines = []

        for i, entry in enumerate(entries):
            full_path = os.path.join(dir_path, entry)
            is_last = i == len(entries) - 1
            connector = "└── " if is_last else "├── "

            tree_lines.append(f"{prefix}{connector}{entry}")

            if os.path.isdir(full_path):
                if "node_modules" in full_path.split(os.sep) or ".gradle" in full_path.split(os.sep):
                    continue
                next_prefix = f"{prefix}{'    ' if is_last else '│   '}"
                tree_lines.extend(build_tree(full_path, next_prefix))

        return tree_lines

    tree = build_tree(root_dir)
    with open(output_file, "w") as f:
        f.write(f"{os.path.basename(root_dir)}/\n")
        f.write("\n".join(tree))

if __name__ == "__main__":
    generate_structure()
